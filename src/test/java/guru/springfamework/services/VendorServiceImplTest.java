package guru.springfamework.services;

import guru.springfamework.api.v1.mapper.VendorMapper;
import guru.springfamework.api.v1.model.VendorDTO;
import guru.springfamework.domain.Vendor;
import guru.springfamework.repositories.VendorRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class VendorServiceImplTest {

    public static final String NAME = "Western Tasty Fruits Ltd.";
    public static final String VENDOR_URL = "/api/v1/vendors/1";
    @Mock
    VendorRepository vendorRepository;

    VendorMapper vendorMapper = VendorMapper.INSTANCE;

    VendorService vendorService;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);

        vendorService = new VendorServiceImpl(vendorRepository, vendorMapper);
    }

    @Test
    public void getAllVendors(){

        //given
        Vendor western = new Vendor();
        western.setName(NAME);
        western.setId(1L);

        Vendor exotic = new Vendor();
        exotic.setId(2l);
        exotic.setName("Exotic Fruits Company");

        Vendor home = new Vendor();
        home.setId(3l);
        home.setName("Home Fruits");

        when(vendorRepository.findAll()).thenReturn(Arrays.asList(western, exotic, home));

        //when
        List<VendorDTO> vendorDTOS = vendorService.getAllVendors();

        //then
        assertEquals(3, vendorDTOS.size());
    }

    @Test
    public void getVendorById() throws Exception{
        //given
        Vendor western = new Vendor();
        western.setName(NAME);
        western.setId(1L);

        Optional<Vendor> vendorOptional = Optional.of(western);

        when(vendorRepository.findById(anyLong())).thenReturn(vendorOptional);

        //when
        VendorDTO vendorDTO = vendorService.getVendorById(1l);

        //then
        assertEquals(NAME, vendorDTO.getName());
    }

    @Test
    public void createNewVendor() throws Exception{

        //given
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName(NAME);

        Vendor savedVendor = new Vendor();
        savedVendor.setName(vendorDTO.getName());
        savedVendor.setId(1l);

        when(vendorRepository.save(any(Vendor.class))).thenReturn(savedVendor);

        //when
        VendorDTO savedDTO = vendorService.createNewVendor(vendorDTO);

        //then
        assertEquals(vendorDTO.getName(), savedDTO.getName());
        assertEquals(VENDOR_URL, savedDTO.getVendorUrl());

    }

    @Test
    public void saveVendorByDTO(){

        //given
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName(NAME);

        Vendor savedVendor = new Vendor();
        savedVendor.setName(vendorDTO.getName());
        savedVendor.setId(1l);

        when(vendorRepository.save(any(Vendor.class))).thenReturn(savedVendor);

        //when
        VendorDTO savedDTO = vendorService.saveVendorByDTO(1L, vendorDTO);

        //then
        assertEquals(vendorDTO.getName(), savedDTO.getName());
        assertEquals(VENDOR_URL, savedDTO.getVendorUrl());
    }

    @Test
    public void deleteVendorById() throws Exception{
        Long id = 1L;

        vendorRepository.deleteById(id);

        verify(vendorRepository, times(1)).deleteById(anyLong());
    }
}
