int computeScore (String word) {
	char[] c=(word.toLowerCase()).toCharArray();	//convert String to an array of char in lower case
	int sum=0;	//
	String letter="abcdefghijklmnopqrstuvwxyz";	//create a string of alphabet
	String value="1332142418513113a11114484a";
	//create the corresponding score in hexadecimal so greater value can also be represented in one character length 
	//(for example "10" would use two-character space, "a" would use one)
	for(int i=0;i<word.length();i++) {
		if(Character.isLetter(c[i])){	//check whether the char is in the alphabet
		char hex=value.charAt(letter.indexOf(""+c[i]));
		//convert char to String so we could check the position of that char in the alphabet string 
		//and then map the corresponding hexadecimal value in the hexadecimal score string to a char
		sum+=Integer.parseInt((""+hex),16);
		//parse the string value in hex to binary stored in int and sum the result for each loop
		}
	}
	return sum;
}
