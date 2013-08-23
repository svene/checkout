package org.svenehrke.checkout.oval;

import net.sf.oval.constraint.Length;
import net.sf.oval.constraint.NotEmpty;
import net.sf.oval.constraint.NotNull;

public class BusinessObject {

	@NotNull
	@NotEmpty
	@Length(max = 32)
	private String name;
}
