boolean checkCardNumber (String cardNumber) {
	char[] c=cardNumber.toCharArray();	//convert the string into an array of character
	int sum=0;
	for(int i=(cardNumber.length()-1);i>=0;i-=2) {
		//Proceeding from the rightmost digit and moving left, sum the value of every odd digit
		sum+=Character.getNumericValue(cardNumber.charAt(i));
	}
	for(int i=(cardNumber.length()-2);i>=0;i-=2) {
		//Proceeding from the rightmost digit and moving left, sum the value of every even or second digit
		int n=Character.getNumericValue(cardNumber.charAt(i));
		if(n>4) {
			sum+=2*n-9;	//if greater than 4 then apply (2n-9)
		}
		else {
			sum+=2*n;
		}
	}
	return (sum%10==0);	//check if it divisible by 10
}
