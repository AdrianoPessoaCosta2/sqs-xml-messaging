#!/bin/bash

aws --endpoint-url=http://localhost:4566 sqs create-queue --queue-name deadletter

DLQ_URL=$(aws --endpoint-url=http://localhost:4566 sqs get-queue-url --queue-name deadletter | jq -r '.QueueUrl')

aws --endpoint-url=http://localhost:4566 sqs create-queue --queue-name main-queue --attributes '{
  "VisibilityTimeout": "10",
  "RedrivePolicy": "{\"deadLetterTargetArn\":\"arn:aws:sqs:us-east-1:000000000000:deadletter\", \"maxReceiveCount\":\"3\"}"
}'