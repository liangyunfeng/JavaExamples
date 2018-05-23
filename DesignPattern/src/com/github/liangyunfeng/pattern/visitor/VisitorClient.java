package com.github.liangyunfeng.pattern.visitor;

import java.util.ArrayList;
import java.util.List;

public class VisitorClient {

	public static void main(String[] args) {
		final ObjectStructure os = new ObjectStructure();
		os.addElement(new GladiolusConcreteElement());
		os.addElement(new ChrysanthemumConreteElement());

		final GladiolusVisitor gVisitor = new GladiolusVisitor();
		final ChrysanthemumVisitor chVisitor = new ChrysanthemumVisitor();

		os.accept(gVisitor);
		os.accept(chVisitor);
	}

}

interface Visitor {
	public void visit(GladiolusConcreteElement gladiolus);

	public void visit(ChrysanthemumConreteElement chrysanthemum);
}

interface FlowerElement {
	public void accept(Visitor visitor);
}

class GladiolusConcreteElement implements FlowerElement {
	@Override
	public void accept(final Visitor visitor) {
		visitor.visit(this);
	}
}

class ChrysanthemumConreteElement implements FlowerElement {
	@Override
	public void accept(final Visitor visitor) {
		visitor.visit(this);
	}
}

class GladiolusVisitor implements Visitor {
	@Override
	public void visit(final GladiolusConcreteElement gladiolus) {
		System.out.println(this.getClass().getSimpleName() + " access "
				+ gladiolus.getClass().getSimpleName());
	}

	@Override
	public void visit(final ChrysanthemumConreteElement chrysanthemum) {
		System.out.println(this.getClass().getSimpleName() + " access "
				+ chrysanthemum.getClass().getSimpleName());
	}
}

class ChrysanthemumVisitor implements Visitor {
	@Override
	public void visit(final GladiolusConcreteElement gladiolus) {
		System.out.println(this.getClass().getSimpleName() + " access "
				+ gladiolus.getClass().getSimpleName());
	}

	@Override
	public void visit(final ChrysanthemumConreteElement chrysanthemum) {
		System.out.println(this.getClass().getSimpleName() + " access "
				+ chrysanthemum.getClass().getSimpleName());
	}
}

class ObjectStructure {
	private final List<FlowerElement> elements = new ArrayList<FlowerElement>();

	public void addElement(final FlowerElement e) {
		elements.add(e);
	}

	public void removeElement(final FlowerElement e) {
		elements.remove(e);
	}

	public void accept(final Visitor visitor) {
		for (final FlowerElement e : elements) {
			e.accept(visitor);
		}
	}
}