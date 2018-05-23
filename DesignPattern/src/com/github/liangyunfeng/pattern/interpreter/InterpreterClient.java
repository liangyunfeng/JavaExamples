package com.github.liangyunfeng.pattern.interpreter;

import java.util.Map;
import java.util.HashMap;

public class InterpreterClient {

	// (a*b)/(a-b+2)
	public static void main(final String[] args) {
		final Context context = new Context();
		context.addValue("a", 7);
		context.addValue("b", 8);
		context.addValue("c", 2);

		final MultiplyNonterminalExpression multiplyValue = new MultiplyNonterminalExpression(
				new TerminalExpression(context.getValue("a")),
				new TerminalExpression(context.getValue("b")));

		final SubtractNonterminalExpression subtractValue = new SubtractNonterminalExpression(
				new TerminalExpression(context.getValue("a")),
				new TerminalExpression(context.getValue("b")));

		final AddNonterminalExpression addValue = new AddNonterminalExpression(
				subtractValue, new TerminalExpression(context.getValue("c")));

		final DivisionNonterminalExpression divisionValue = new DivisionNonterminalExpression(
				multiplyValue, addValue);

		System.out.println(divisionValue.interpreter(context));
	}

}

class Context {
	private final Map<String, Integer> valueMap = new HashMap<String, Integer>();

	public void addValue(final String key, final int value) {
		valueMap.put(key, Integer.valueOf(value));
	}

	public int getValue(final String key) {
		return valueMap.get(key).intValue();
	}
}

abstract class AbstractExpression {
	public abstract int interpreter(Context context);
}

class AddNonterminalExpression extends AbstractExpression {
	private final AbstractExpression left;
	private final AbstractExpression right;

	public AddNonterminalExpression(final AbstractExpression left,
			final AbstractExpression right) {
		this.left = left;
		this.right = right;
	}

	@Override
	public int interpreter(final Context context) {
		return this.left.interpreter(context) + this.right.interpreter(context);
	}

}

class DivisionNonterminalExpression extends AbstractExpression {
	private final AbstractExpression left;
	private final AbstractExpression right;

	public DivisionNonterminalExpression(final AbstractExpression left,
			final AbstractExpression right) {
		this.left = left;
		this.right = right;
	}

	@Override
	public int interpreter(final Context context) {
		final int value = this.right.interpreter(context);
		if (value != 0) {
			return this.left.interpreter(context) / value;
		}
		return -1111;
	}

}

class MultiplyNonterminalExpression extends AbstractExpression {
	private final AbstractExpression left;
	private final AbstractExpression right;

	public MultiplyNonterminalExpression(final AbstractExpression left,
			final AbstractExpression right) {
		this.left = left;
		this.right = right;
	}

	@Override
	public int interpreter(final Context context) {
		return this.left.interpreter(context) * this.right.interpreter(context);
	}

}

class SubtractNonterminalExpression extends AbstractExpression {
	private final AbstractExpression left;
	private final AbstractExpression right;

	public SubtractNonterminalExpression(final AbstractExpression left,
			final AbstractExpression right) {
		this.left = left;
		this.right = right;
	}

	@Override
	public int interpreter(final Context context) {
		return this.left.interpreter(context) - this.right.interpreter(context);
	}
}

class TerminalExpression extends AbstractExpression {
	private final int i;

	public TerminalExpression(final int i) {
		this.i = i;
	}

	@Override
	public int interpreter(final Context context) {
		return this.i;
	}

}
