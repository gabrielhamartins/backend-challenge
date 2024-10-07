variable "aws_access_key" {
  type        = string
  description = "use env aws keys"
}

variable "aws_secret_key" {
  type        = string
  description = "use env aws keys"
}

variable "aws_region" {
  default = "us-east-2"
}

variable "desired_capacity" {
  description = "desired number of running nodes"
  default     = 2
}

variable "container_port" {
  default = "8080"
}

variable "image_url" {
  default = "<seu-id-da-conta>.dkr.ecr.us-east-2.amazonaws.com/backend-challenge-container:latest"
}

variable "memory" {
  default = "1024"
}

variable "cpu" {
  default = "512"
}

variable "cluster_name" {
  default = "backend-challenge-cluster"
}

variable "cluster_task" {
  default = "backend-challenge-task"
}
variable "cluster_service" {
  default = "backend-challenge-service"
}
