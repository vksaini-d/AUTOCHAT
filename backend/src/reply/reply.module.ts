import { Module } from '@nestjs/common';
import { ReplyService } from './reply.service';
import { PersonasModule } from '../personas/personas.module';

@Module({
  imports: [PersonasModule],
  providers: [ReplyService],
  exports: [ReplyService],
})
export class ReplyModule {}
