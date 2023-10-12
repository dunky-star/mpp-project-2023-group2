package librarysystem.rulesets;

import business.Group2Exception;
import librarysystem.AddBookCopyWindow;

import java.awt.*;

/**
 * Rules:
 * 1. All fields must be nonempty
 */
public class AddBookCopyRuleSet implements RuleSet {
	private AddBookCopyWindow bookWindow;
	@Override
	public void applyRules(Component ob) throws Group2Exception {
		bookWindow = (AddBookCopyWindow) ob;
		nonemptyRule();
	}

	private void nonemptyRule() throws Group2Exception {
		if(bookWindow.getISBN().trim().isEmpty()) {
			throw new Group2Exception("Field must be non-empty!");
		}
	}
}
