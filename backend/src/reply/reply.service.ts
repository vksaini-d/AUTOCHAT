import { Injectable, Logger } from '@nestjs/common';
import { PersonasService } from '../personas/personas.service';
import { GoogleGenerativeAI } from '@google/generative-ai';
import { ConfigService } from '@nestjs/config';
import fetch from 'node-fetch'; // Make sure to install node-fetch if not present in Node 18+

@Injectable()
export class ReplyService {
  private readonly logger = new Logger(ReplyService.name);
  private genAI: GoogleGenerativeAI;
  private model: any;

  constructor(
    private personasService: PersonasService,
    private configService: ConfigService,
  ) {
    const geminiKey = this.configService.get<string>('GEMINI_API_KEY');
    if (geminiKey) {
      this.genAI = new GoogleGenerativeAI(geminiKey);
      this.model = this.genAI.getGenerativeModel({ model: 'gemini-pro' });
    }
  }

  async processMessage(userId: string, normalizedMessage: any) {
    this.logger.log(`Processing message for user ${userId}`);

    const personas = await this.personasService.findAll(userId);
    const activePersona = personas.find((p) => p.isActive);

    if (!activePersona) {
      this.logger.log('No active persona found. Skipping reply.');
      return null;
    }

    try {
        // Try Gemini first, fallback to Hugging Face
        let replyText = "";
        if (this.model) {
             replyText = await this.generateGeminiReply(normalizedMessage.raw.text, activePersona);
        } else {
             replyText = await this.generateHuggingFaceReply(normalizedMessage.raw.text, activePersona);
        }
    
        const finalReply = `${replyText}\n\n(Auto-reply by ChatTune)`;
        this.logger.log(`Generated Reply: ${finalReply}`);
        return finalReply;
    } catch (error) {
        this.logger.error('Failed to generate reply', error);
        return null;
    }
  }

  private async generateGeminiReply(input: string, persona: any): Promise<string> {
    const prompt = this.buildPrompt(input, persona);
    const result = await this.model.generateContent(prompt);
    const response = await result.response;
    return response.text();
  }

  private async generateHuggingFaceReply(input: string, persona: any): Promise<string> {
    const hfToken = this.configService.get<string>('HF_API_KEY');
    if (!hfToken) throw new Error("No AI Provider configured (Gemini or HuggingFace)");

    const prompt = this.buildPrompt(input, persona);
    
    // Using a free model like Mistral-7B or similar hosted on HF Inference API
    const response = await fetch(
        "https://api-inference.huggingface.co/models/mistralai/Mistral-7B-Instruct-v0.2",
        {
            headers: { Authorization: `Bearer ${hfToken}` },
            method: "POST",
            body: JSON.stringify({ inputs: prompt }),
        }
    );
    
    const result = await response.json();
    // HF returns array of generated text
    if (Array.isArray(result) && result.length > 0) {
        // Simple parsing, might need adjustment based on exact model output
        return result[0].generated_text.replace(prompt, '').trim(); 
    }
    return "I'm not sure what to say.";
  }

  private buildPrompt(input: string, persona: any): string {
    return `
      You are an AI assistant acting on behalf of a user.
      Incoming Message: "${input}"
      Your Persona Settings:
      - Tone: ${persona.chatTune}
      - Emotion: ${persona.emotionalTone}
      - Behavior: ${persona.behavior}
      Instructions: Reply to the message acting exactly according to the persona settings above. Keep the reply concise. Do not include quotes.
    `;
  }
}
