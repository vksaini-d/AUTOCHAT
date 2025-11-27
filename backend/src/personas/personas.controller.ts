import { Controller, Get, Post, Body, Patch, Param, Delete, UseGuards, Request } from '@nestjs/common';
import { PersonasService } from './personas.service';
import { CreatePersonaDto } from './dto/create-persona.dto';
import { JwtAuthGuard } from '../auth/jwt-auth.guard';
import { ApiBearerAuth, ApiTags } from '@nestjs/swagger';

@ApiTags('personas')
@ApiBearerAuth()
@UseGuards(JwtAuthGuard)
@Controller('personas')
export class PersonasController {
  constructor(private readonly personasService: PersonasService) {}

  @Post()
  create(@Request() req, @Body() createPersonaDto: CreatePersonaDto) {
    return this.personasService.create(req.user.userId, createPersonaDto);
  }

  @Get()
  findAll(@Request() req) {
    return this.personasService.findAll(req.user.userId);
  }

  @Get(':id')
  findOne(@Param('id') id: string) {
    return this.personasService.findOne(id);
  }

  @Patch(':id')
  update(@Param('id') id: string, @Body() updatePersonaDto: Partial<CreatePersonaDto>) {
    return this.personasService.update(id, updatePersonaDto);
  }

  @Delete(':id')
  remove(@Param('id') id: string) {
    return this.personasService.remove(id);
  }
}
