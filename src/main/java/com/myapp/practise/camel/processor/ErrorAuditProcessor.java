package com.myapp.practise.camel.processor;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ErrorAuditProcessor implements Processor {

	@Override
	public void process(Exchange exchange) {

		Exception ex = exchange.getProperty(
				Exchange.EXCEPTION_CAUGHT,
				Exception.class);

		String fileName =
				exchange.getIn().getHeader(
						"CamelFileName",
						String.class);

		log.info("AUDIT => File: {}", fileName);
		log.info("Error: {}" , ex.getMessage());

		// errorAuditRepository.save(...)
	}
}
