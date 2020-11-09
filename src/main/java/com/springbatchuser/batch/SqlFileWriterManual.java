package com.springbatchuser.batch;

import java.io.IOException;
import java.util.List;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.PassThroughLineMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Component;
@Component
public class SqlFileWriterManual implements ItemWriter<String> {
	
	

	

	public FlatFileItemReader<String> readerSql5() {
		// Create reader instance
		FlatFileItemReaderBuilder<String> reader = new FlatFileItemReaderBuilder<String>();

		
		System.out.println("reader4 reads value");
		return new FlatFileItemReaderBuilder<String>()
				.name("readerSql5")
	            .lineMapper(new PassThroughLineMapper())
	            .build();
	}
	
		 public  MultiResourceItemReader<String> multiResourceItemReaderSql5() {
		MultiResourceItemReader<String> resourceItemReader4 = new MultiResourceItemReader<String>();
		
		    	// if(LoadController.getRes() !=null) {
		    	//Resource[] ref = LoadController.getRes().;
		ClassLoader cl = this.getClass().getClassLoader();
		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver(cl);
		// Resource[] res = null;

		Resource[] reso = null;
		try {
			reso = resolver.getResources("file:c:/afiles/sqlfiles/*.sql");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (Resource resource : reso) {
			System.out.println("resource name " + resource.getFilename());
		}
		System.out.println("executes multi item reader");
		

					    	resourceItemReader4.setResources(reso);
							resourceItemReader4.setDelegate(readerSql5());
							
							
				
				return resourceItemReader4;
				
	}
	@Override
	public void write(List<? extends String> values) throws Exception {
		
		
		List<String> lisVal = (List<String>) multiResourceItemReaderSql5();
		System.out.println("available files");
		for (String string : lisVal) {
			System.out.println(string);
		}
		
		
		
	}

}
