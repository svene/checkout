package org.svenehrke.checkout.oval;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ValidatorTest {

	@Test
	public void test1() throws Exception {
		Validator validator = new Validator();
		BusinessObject bo = new BusinessObject(); // name is null

		List<ConstraintViolation> violations = validator.validate(bo);

		assertThat(violations).hasSize(1);
		assertThat(violations.get(0).getMessage()).isEqualTo("org.svenehrke.checkout.oval.BusinessObject.name cannot be null");
	}

}
