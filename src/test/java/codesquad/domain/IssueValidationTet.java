package codesquad.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import codesquad.dto.IssueDto;

public class IssueValidationTet {
	private static final Logger log = LoggerFactory.getLogger(IssueValidationTet.class);

	private static Validator validator;

	@BeforeClass
	public static void setup() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	public void issueSubejectWhenIsEmpty() {
		IssueDto issueDto = new IssueDto(1L, "", "내용");
		Set<ConstraintViolation<IssueDto>> constraintViolations = validator.validate(issueDto);
		assertThat(constraintViolations.size(), is(1));
		for (ConstraintViolation<IssueDto> constraitViolation : constraintViolations) {
			log.debug("violation error message :{}", constraitViolation.getMessage());
		}

		issueDto = new IssueDto(1L, "", "");
		constraintViolations = validator.validate(issueDto);
		assertThat(constraintViolations.size(), is(2));
		
		for (ConstraintViolation<IssueDto> constraitViolation : constraintViolations) {
			log.debug("violation error message :{}", constraitViolation.getMessage());
		}

	}

}
