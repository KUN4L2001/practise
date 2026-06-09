package com.myapp.practise.camel.route;

import com.myapp.practise.camel.processor.ErrorAuditProcessor;
import com.myapp.practise.camel.processor.FileProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExcelFileRoute extends RouteBuilder {

	@Autowired
	private FileProcessor fileProcessor;

	@Autowired
	private ErrorAuditProcessor errorAuditProcessor;

	@Override
	public void configure() throws Exception {
		onException(Exception.class)
				.process(errorAuditProcessor)
				.handled(true)
				.log("Error occurred: ${exception.message}");

		from("file:/home/kunal-gaikwad/Practice/apache-camel-files/input?delete=true")
				.log("File: ${header.CamelFileName}")
				.process(fileProcessor)
//				.process(exchange -> {
//					throw new RuntimeException("Something went wrong");
//				})
				.to("file:/home/kunal-gaikwad/Practice/apache-camel-files/output");
	}
}
