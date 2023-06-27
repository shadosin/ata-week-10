echo "Deploying Week 10 Resources - DynamoDB"
aws cloudformation create-stack --stack-name dynamodbannotationsloadsave-bookstable01 --template-body file://deploy/DynamoDbAnnotationsLoadSave.yaml --capabilities CAPABILITY_IAM --tags '[{"Key": "Week","Value": "10" },{"Key": "CourseName","Value": "ata"},{"Key": "ForStudents","Value": "true"}]'
aws cloudformation create-stack --stack-name dynamodbannotationsloadsave-videogame --template-body file://deploy/DynamoDbAnnotationsLoadSave-VideoGame.yaml --capabilities CAPABILITY_IAM --tags '[{"Key": "Week","Value": "10" },{"Key": "CourseName","Value": "ata"},{"Key": "ForStudents","Value": "true"}]'
aws cloudformation create-stack --stack-name dynamodbannotationsloadsave-actormovie --template-body file://deploy/DynamoDbAnnotationsLoadSave-ActorMovie.yaml --capabilities CAPABILITY_IAM --tags '[{"Key": "Week","Value": "10" },{"Key": "CourseName","Value": "ata"},{"Key": "ForStudents","Value": "true"}]'
echo "Waiting for it to finish deploying.  This may take 2-3 minutes...  But if takes more than 5 minutes then it may have failed. Check your CloudFormation Stack on the AWS UI for errors."
aws cloudformation wait stack-create-complete --stack-name dynamodbannotationsloadsave-bookstable01
aws cloudformation wait stack-create-complete --stack-name dynamodbannotationsloadsave-videogame
aws cloudformation wait stack-create-complete --stack-name dynamodbannotationsloadsave-actormovie
echo "Done!"