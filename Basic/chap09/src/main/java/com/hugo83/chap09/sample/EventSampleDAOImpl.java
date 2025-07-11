package com.hugo83.chap09.sample;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("event")
public class EventSampleDAOImpl implements SampleDAO {
}
