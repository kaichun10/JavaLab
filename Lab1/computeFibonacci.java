int computeFibonacci(int n) {
	int result=1,lastresult=1;
	for (int i=0; i<(n-2); i++) {
		int temp=result;
		result+=lastresult;
		lastresult=temp;
	}
	return result;
}
