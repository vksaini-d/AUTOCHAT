import { Controller, Post, Param, Body } from '@nestjs/common';
import { PlatformsService } from './platforms.service';
import { ApiTags, ApiOperation } from '@nestjs/swagger';

@ApiTags('platforms')
@Controller('webhooks')
export class PlatformsController {
  constructor(private readonly platformsService: PlatformsService) {}

  @Post(':platform/incoming')
  @ApiOperation({ summary: 'Receive incoming webhook from a platform' })
  handleWebhook(@Param('platform') platform: string, @Body() payload: any) {
    return this.platformsService.handleWebhook(platform, payload);
  }
}
