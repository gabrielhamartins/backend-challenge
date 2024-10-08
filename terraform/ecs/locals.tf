locals {

  subnet_ids = { for k, v in aws_subnet.this : v.tags.Name => v.id }

  common_tags = {
    Project   = "ECS Fargate"
    CreatedAt = "2024-10-07"
    ManagedBy = "Backend Challenge"
    Owner     = "Gabriel Martins"
    Service   = "ECS Fargate"
  }
}