import { Module } from '@nestjs/common';
import { ConfigModule } from '@nestjs/config';
import { PrismaModule } from './prisma/prisma.module';
import { AuthModule } from './auth/auth.module';
import { UsersModule } from './users/users.module';
import { PersonasModule } from './personas/personas.module';
import { PlatformsModule } from './platforms/platforms.module';
import { ReplyModule } from './reply/reply.module';

@Module({
  imports: [
    ConfigModule.forRoot({
      isGlobal: true,
    }),
    PrismaModule,
    AuthModule,
    UsersModule,
    PersonasModule,
    PlatformsModule,
    ReplyModule,
  ],
  controllers: [],
  providers: [],
})
export class AppModule {}
