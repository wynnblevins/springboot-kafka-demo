package com.wynnblevins.springboot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.wynnblevins.springboot.entity.WikimediaData;
import com.wynnblevins.springboot.repository.WikimediaDataRepository;

@Service
public class KafkaDatabaseConsumer {
	private static final Logger LOGGER = LoggerFactory.getLogger(KafkaDatabaseConsumer.class);
	
	@Autowired
	private WikimediaDataRepository dataRepository;
	
	public KafkaDatabaseConsumer(WikimediaDataRepository dataRepository) {
		super();
		this.dataRepository = dataRepository;
	}

	@KafkaListener(topics = "wikimedia_recentchange", groupId = "myGroup")
	public void consumer(String eventMessage) {
		LOGGER.info(String.format("Event message received -> %s", eventMessage));
		
		WikimediaData wikimediaData = new WikimediaData();
		wikimediaData.setWikiEventData(eventMessage);
		
		dataRepository.save(wikimediaData);
	}
}
