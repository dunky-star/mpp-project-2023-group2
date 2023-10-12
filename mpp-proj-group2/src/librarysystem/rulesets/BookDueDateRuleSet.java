package librarysystem.rulesets;

import business.Group2Exception;
import librarysystem.BookDueDateWindow;

import java.awt.*;

/**
 * Rules:
 * 1. All fields must be nonempty
 *
 */
public class BookDueDateRuleSet implements RuleSet {
	private BookDueDateWindow bookWindow;
	@Override
	public void applyRules(Component ob) throws Group2Exception {
		bookWindow = (BookDueDateWindow) ob;
		nonemptyRule();
	}

	private void nonemptyRule() throws Group2Exception {
		if(bookWindow.getISBNString().trim().isEmpty()) {
			throw new Group2Exception("Field must be non-empty!");
		}
	}
}
