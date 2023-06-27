# Kenzie Java Project

Follow the instructions on lms.kenzie.academy to complete this assignment.

## AWS Deploy Commands:

* Book Table - `aws cloudformation create-stack --stack-name dynamodbannotationsloadsave-bookstable01 --template-body file://DynamoDbAnnotationsLoadSave.yaml --capabilities CAPABILITY_IAM`
* Movie and Actor DB - `aws cloudformation create-stack --stack-name dynamodbannotationsloadsave-actormovie --template-body file://DynamoDbAnnotationsLoadSave-ActorMovie.yaml --capabilities CAPABILITY_IAM`
* Video Games - `aws cloudformation create-stack --stack-name dynamodbannotationsloadsave-videogame --template-body file://DynamoDbAnnotationsLoadSave-VideoGame.yaml --capabilities CAPABILITY_IAM`
* Group Work - `aws cloudformation create-stack --stack-name groupwork-discussion-cli --template-body file://GroupworkDiscussionCli.yaml --capabilities CAPABILITY_IAM`

## Gradle Commands 

### DynamoDB / Annotations

* `./gradlew dynamodb-annotations-save-test` - Run Unit Tests for the Book Integration program
* `./gradlew dynamodb-annotations-actor-movie-test` - Run Unit Tests for the ActorMovie program
* `./gradlew dynamodb-annotations-video-game-test` - Run Unit Tests for the VideoGame program

### CustomExceptions/UserExistsException

* `./gradlew customexceptions-userexistsexception-test` - Run Unit Tests for the UserExistsException Program

### CustomExceptions/IceCreamParlor

* `customexceptions-icecreamparlor-phase3` - Runs All Unit Tests for the IceCreamParlor program phase 3
* `customexceptions-icecreamparlor-phase4` - Runs All Unit Tests for the IceCreamParlor program phase 3 and 4
* `customexceptions-icecreamparlor-phase5` - Runs All Unit Tests for the IceCreamParlor program phase 3, 4, and 5

### GroupWork

To run all Tests
* `./gradlew groupwork-discussion-test`

To Run the CLI application 
* `./gradlew groupwork-discussion-cli`
