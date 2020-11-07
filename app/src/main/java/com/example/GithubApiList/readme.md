// Initialize the Amazon Cognito credentials provider
CognitoCachingCredentialsProvider credentialsProvider = new CognitoCachingCredentialsProvider(
    getApplicationContext(),
    "eu-central-1:bb36451b-571a-47c8-8394-8867f6630db6", // Identity pool ID
    Regions.EU_CENTRAL_1 // Region
);