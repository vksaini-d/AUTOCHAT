import { Module } from '@nestjs/common';
import { PlatformsService } from './platforms.service';
import { PlatformsController } from './platforms.controller';
import { ReplyModule } from '../reply/reply.module';

@Module({
  imports: [ReplyModule],
  controllers: [PlatformsController],
  providers: [PlatformsService],
})
export class PlatformsModule {}
