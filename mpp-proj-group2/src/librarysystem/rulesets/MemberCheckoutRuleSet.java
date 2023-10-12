package librarysystem.rulesets;

import business.Group2Exception;
import librarysystem.MemberCheckoutRecordWindow;

import java.awt.*;

/**
 * Rules:
 *  1. All fields must be nonempty
 */

public class MemberCheckoutRuleSet implements RuleSet {
		private MemberCheckoutRecordWindow window;
	@Override
	public void applyRules(Component ob) throws Group2Exception {
		window = (MemberCheckoutRecordWindow) ob;
		nonEmptyRule();
	}

	private void nonEmptyRule() throws Group2Exception {
		if(window.getMemberID().trim().isEmpty()
		) {
			throw new Group2Exception("Member ID must be non-empty!");
		}
	}


	
}