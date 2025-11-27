import { IsBoolean, IsNotEmpty, IsOptional, IsString } from 'class-validator';
import { ApiProperty } from '@nestjs/swagger';

export class CreatePersonaDto {
  @ApiProperty()
  @IsNotEmpty()
  @IsString()
  name: string;

  @ApiProperty()
  @IsNotEmpty()
  @IsString()
  chatTune: string;

  @ApiProperty()
  @IsNotEmpty()
  @IsString()
  emotionalTone: string;

  @ApiProperty()
  @IsNotEmpty()
  @IsString()
  behavior: string;

  @ApiProperty({ required: false })
  @IsOptional()
  @IsBoolean()
  isActive?: boolean;
}
