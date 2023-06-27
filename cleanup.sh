echo "Deleting Resources for Week 10"
aws cloudformation delete-stack --stack-name dynamodbannotationsloadsave-bookstable01
aws cloudformation delete-stack --stack-name dynamodbannotationsloadsave-videogame
aws cloudformation delete-stack --stack-name dynamodbannotationsloadsave-actormovie
echo "This may take 2-3 minutes...  But if takes more than 5 minutes then it may have failed. Check your CloudFormation Stack on the AWS UI for errors."
aws cloudformation wait stack-delete-complete --stack-name dynamodbannotationsloadsave-bookstable01
aws cloudformation wait stack-delete-complete --stack-name dynamodbannotationsloadsave-videogame
aws cloudformation wait stack-delete-complete --stack-name dynamodbannotationsloadsave-actormovie
echo "Done!"
