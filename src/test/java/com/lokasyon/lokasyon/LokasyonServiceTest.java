package com.lokasyon.lokasyon;

import com.lokasyon.lokasyon.repository.LokasyonRepository;
import com.lokasyon.lokasyon.service.LokasyonService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class LokasyonServiceTest {
    @Mock
    private LokasyonRepository lokasyonRepository;

    @InjectMocks
    private LokasyonService lokasyonService;

    @Test
    public void LokasyonService_ListOfCity
}
