import { Injectable } from '@nestjs/common';
import { PrismaService } from '../prisma/prisma.service';
import { CreatePersonaDto } from './dto/create-persona.dto';

@Injectable()
export class PersonasService {
  constructor(private prisma: PrismaService) {}

  async create(userId: string, createPersonaDto: CreatePersonaDto) {
    return this.prisma.persona.create({
      data: {
        ...createPersonaDto,
        userId,
      },
    });
  }

  async findAll(userId: string) {
    return this.prisma.persona.findMany({
      where: { userId },
    });
  }

  async findOne(id: string) {
    return this.prisma.persona.findUnique({
      where: { id },
    });
  }

  async update(id: string, updatePersonaDto: Partial<CreatePersonaDto>) {
    return this.prisma.persona.update({
      where: { id },
      data: updatePersonaDto,
    });
  }

  async remove(id: string) {
    return this.prisma.persona.delete({
      where: { id },
    });
  }
}
