package com.scsb;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.scsb.config.DataOption;
import com.scsb.model.Sheet;
import com.scsb.model.repository.SheetRepository;
import com.scsb.service.SheetService;

@SpringBootTest
class ScsbSpringbootApplicationTests {
	
	@Autowired
	SheetService service;
	@Autowired
	SheetRepository repositoy;
	@Autowired
	DataOption option;
	
	@Test
	void contextLoads() throws InterruptedException, CloneNotSupportedException {
		Sheet sheet = service.getSheetById(161);
		List<Sheet> addSheet = new ArrayList<Sheet>(100);
		Map<String, String> optionMap = option.getSheetCategoryByIndex(DataOption.INDEX_CARD_SWIPE_HOT_CATEGORYS);
		List<String> keyIndex = new ArrayList<String>(optionMap.keySet());
		int max = optionMap.size();
		Random rnd = new Random();
		
		for (int i = 1; i <= 200; i++) {
			sheet.setId(sheet.getId() + 1);
			sheet.setCategory(keyIndex.get(rnd.nextInt(max)));
//			addSheet.add(sheet.clone());
			Thread.sleep(100);
			repositoy.save(sheet);
		}
//		repositoy.saveAll(addSheet);
	}

}
