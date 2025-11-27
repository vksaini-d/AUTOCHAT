import { Injectable, Logger } from '@nestjs/common';
import { ReplyService } from '../reply/reply.service';

@Injectable()
export class PlatformsService {
  private readonly logger = new Logger(PlatformsService.name);

  constructor(private replyService: ReplyService) {}

  async handleWebhook(platform: string, payload: any) {
    this.logger.log(`Received webhook from ${platform}: ${JSON.stringify(payload)}`);
    
    // Normalize message
    const normalizedMessage = this.normalizeMessage(platform, payload);
    
    // Mock User ID (In real app, derive from payload/platform ID)
    const mockUserId = "user-123"; 

    // Process Reply
    const reply = await this.replyService.processMessage(mockUserId, normalizedMessage);
    
    if (reply) {
        // TODO: Send reply back to Platform API
        this.logger.log(`Sending Reply to ${platform}: ${reply}`);
    }
    
    return { status: 'received' };
  }

  private normalizeMessage(platform: string, payload: any) {
    // Mock normalization logic
    return {
      platform,
      raw: payload,
      timestamp: new Date(),
    };
  }
}
