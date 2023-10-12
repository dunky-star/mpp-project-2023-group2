package librarysystem.rulesets;

import business.Group2Exception;
import librarysystem.AddBookWindow;

import java.awt.*;

/**
 * Rules:
 * 1. All fields must be nonempty
 * 2. Max length check out must be a number and large than 0.
 * 3. Number of copies must be a number and large than 0.
 */
public class AddBookRuleSet implements RuleSet {
	private AddBookWindow bookWindow;
	@Override
	public void applyRules(Component ob) throws Group2Exception {
		bookWindow = (AddBookWindow) ob;
		nonemptyRule();
		maxLengthCheckoutNumericRule();
		numberOfCopiesNumericRule();
	}

	private void nonemptyRule() throws Group2Exception {
		if(bookWindow.getISBN().trim().isEmpty() ||
				bookWindow.getBookTitle().trim().isEmpty()||
				bookWindow.getMaxCheckOut().trim().isEmpty()||
				bookWindow.getMaxCheckOut().trim().isEmpty()) {
			throw new Group2Exception("Field must be non-empty!");
		}
	}

	private void maxLengthCheckoutNumericRule() throws Group2Exception {
		String val = bookWindow.getMaxCheckOut();
		try {
			int maxLengthCheckout = Integer.parseInt(val);

			if(maxLengthCheckout < 0)
				throw new Group2Exception("Max length check out must large than 0");

		} catch(NumberFormatException e) {
			throw new Group2Exception("Max length check out must be a number");
		}
	}

	private void numberOfCopiesNumericRule() throws Group2Exception {
		String val = bookWindow.getNumberOfCopies();
		try {
			int numberOfCopies = Integer.parseInt(val);

			if(numberOfCopies < 0)
				throw new Group2Exception("Number of copies must large than 0");

		} catch(NumberFormatException e) {
			throw new Group2Exception("Number of copies must be a number");
		}
	}
}
