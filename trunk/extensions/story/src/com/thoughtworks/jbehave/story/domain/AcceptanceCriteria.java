/*
 * Created on 28-Sep-2004
 * 
 * (c) 2003-2004 ThoughtWorks Ltd
 *
 * See license.txt for license details
 */
package com.thoughtworks.jbehave.story.domain;

import java.util.List;

import com.thoughtworks.jbehave.story.visitor.Visitable;
import com.thoughtworks.jbehave.story.visitor.VisitableArrayList;
import com.thoughtworks.jbehave.story.visitor.Visitor;

/**
 * @author <a href="mailto:dan.north@thoughtworks.com">Dan North</a>
 */
public class AcceptanceCriteria implements Visitable {
	private final VisitableArrayList scenarios = new VisitableArrayList();

	public List scenarios() {
        return scenarios;
    }

	public void accept(Visitor visitor) {
		visitor.visitAcceptanceCriteria(this);
		scenarios.accept(visitor);
	}

	public void addScenario(Scenario scenario) {
		scenarios.add(scenario);
	}
}