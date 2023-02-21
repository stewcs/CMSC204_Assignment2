public class Notation {
	
	/**
	 * This class contains methods that manipulate infix and postfix expressions.
	 * @author Daniel Xu
	 * @version 2/20/2023
	 */

	/**
	 * 
	 * @param s the infix string to be tested 
	 * @return true if parentheses are valid, false if not.
	 */
	public static boolean validP(String s) {
		MyStack<Character> stack = new MyStack<Character>(s.length());

		char check = 0;

		for (int i = 0; i < s.length(); i++) {

			if (s.charAt(i) == '(')
				stack.push(s.charAt(i));

			else if (s.charAt(i) == ')') {

				if(stack.isEmpty()) {
					throw new InvalidNotationFormatException();
				}
				check = stack.pop();
				if (check != '(')
					throw new InvalidNotationFormatException();
			}
		}

		return true;
	}

	/**
	 * 
	 * @param s the postfix string to be tested
	 * @return true if parentheses are valid, false if not.
	 */
	public static boolean validP2(String s) {
		for(int i = 0; i < s.length()-2; i++) {
			char c1 = s.charAt(i);
			char c2 = s.charAt(i+1);
			char c3 = s.charAt(i+2);
			
			if (checkOp(c1) && checkOp(c2) && checkOp(c3)) {
			
				throw new InvalidNotationFormatException();

			}
		}
		return true;
	}

	public static boolean checkOp(char c) {
		return c == '+' || c == '-' || c == '*' || c == '/';
	}


	/**
	 * 
	 * @param s the string to be converted
	 * @return postfix the converted postfix string
	 * @throws InvalidNotationFormatException
	 */
	public static String convertInfixToPostfix (String s) throws InvalidNotationFormatException{
		MyStack<Character> stack = new MyStack<Character>(s.length());
		String postfix = "";

		for(int i = 0; i < s.length(); i++) {
			Notation.validP(s);
			char nextOperator;
			char current = s.charAt(i);

			switch (current) {

			case '+': case '-': case '*': case '/':

				stack.push(current);
				break;
			case '(':

				stack.push(current); 
				break;
			case ')': 

				while(stack.top() != '(') {
					nextOperator = stack.pop();
					postfix += nextOperator;
					break;	
				}
				stack.pop();
				break;

			default: 

				postfix += current; 
			}
		}
		return postfix;
	}

	/**
	 * 
	 * @param s the string to be converted
	 * @return infix the converted infix string
	 * @throws InvalidNotationFormatException
	 */
	public static String convertPostfixToInfix (String s) throws InvalidNotationFormatException{
		MyStack<String> stack = new MyStack<String>(s.length());
		String infix = "";
		String temp = "";
		Notation.validP2(s);
		
		for(int i = 0; i < s.length(); i++) {
			
			String operandOne;
			String operandTwo;
			char nextOperator;
			char current = s.charAt(i);

			switch (current) {
			case '+': case '-': case '*': case '/':
				if(stack.size() >= 2) {
					temp = "";
					operandTwo = stack.pop();
					operandOne = stack.pop();
					temp += "(";
					temp += operandOne;
					temp += current;
					temp += operandTwo;
					temp += ")";
					stack.push(temp);

				}else { // if less than two values when popping
					throw new InvalidNotationFormatException();
				}
			case ' ': // if space, do nothing
				break;
			default:	
				stack.push(current + "");

			}
		}
		return infix += stack.top();
	}

	/**
	 * @param s the string to be evaluated
	 * @return the evaluated string
	 * @throws InvalidNotationFormatException
	 */
	public static double evaluatePostfixExpression (String s) throws InvalidNotationFormatException{
		MyStack<Double> stack = new MyStack<Double>(s.length());
		Notation.validP2(s);
		for(int i = 0; i < s.length(); i++) {
			double operandOne;
			double operandTwo;
			double result;
			char next = s.charAt(i);
			switch (next) {

			case '+': case '-': case '*': case '/':
				operandTwo = stack.pop();

				operandOne = stack.pop();

				if(s.charAt(i) == '+') { result = operandOne + operandTwo; stack.push(result);}
				if(s.charAt(i) == '-') { result = operandOne - operandTwo; stack.push(result);}	
				if(s.charAt(i) == '*') { result = operandOne * operandTwo; stack.push(result);}
				if(s.charAt(i) == '/') { result = operandOne / operandTwo; stack.push(result);}
				break;
			default:

				stack.push((double)Character.getNumericValue(next));
			}		
		}
		return stack.top();
	}
}
