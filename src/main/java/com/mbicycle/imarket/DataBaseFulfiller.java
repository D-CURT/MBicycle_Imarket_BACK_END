package com.mbicycle.imarket;

import com.mbicycle.imarket.beans.entities.*;
import com.mbicycle.imarket.daos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;

import static com.mbicycle.imarket.utils.enums.RoleType.ADMIN;
import static com.mbicycle.imarket.utils.enums.RoleType.MANAGER;
import static com.mbicycle.imarket.utils.enums.RoleType.CUSTOMER;

//@Component
@SuppressWarnings("ALL")
public class DataBaseFulfiller implements CommandLineRunner {
    @Autowired
    UserRepository userRepository;

    @Autowired
    ProfileRepository profileRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    GroupRepository groupRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public void run(String...args) throws Exception {

        User userAdmin = new User("admin", passwordEncoder.encode("123"), Collections.singletonList(ADMIN.getRole()));
        User userManager = new User("manager", passwordEncoder.encode("123"), Collections.singletonList(MANAGER.getRole()));
        User userCustomer = new User("customer", passwordEncoder.encode("123"), Collections.singletonList(CUSTOMER.getRole()));

        userRepository.save(userAdmin);
        userRepository.save(userManager);
        userRepository.save(userCustomer);

        Profile profileAdmin = new Profile();
        profileAdmin.setName("АДМИНИСТРАТОР АДМИН АДМИНОВИЧ");
        profileAdmin.setEmail("ADMIN@ADMIN.COM");
        profileAdmin.setUser(userRepository.findByLogin(userAdmin.getLogin()));
        profileRepository.save(profileAdmin);

        Profile profileManager = new Profile();
        profileManager.setName("Manager Manag Managovi4");
        profileManager.setEmail("Manager_Imarket@Imarket.COM");
        profileManager.setUser(userRepository.findByLogin(userManager.getLogin()));
        profileRepository.save(profileManager);

        Profile profileCustomer = new Profile();
        profileCustomer.setName("Покупательный Имя Отвествов");
        profileCustomer.setEmail("nubas@mail.ru");
        profileCustomer.setUser(userRepository.findByLogin(userCustomer.getLogin()));
        profileRepository.save(profileCustomer);

        Category categoryComputerSystems = new Category("Computer Systems");
            Group groupLaptops = new Group("Laptops", categoryComputerSystems);
            Group groupPCs = new Group("PCs", categoryComputerSystems);

        Category categoryComputerComponents = new Category("Computer Components");
            Group groupKeyboards = new Group("Keyboards", categoryComputerComponents);
            Group groupGraphicsCard = new Group("Graphics Card", categoryComputerComponents);

        Category categoryPrintersScanners = new Category("Printers & Scanners");
            Group groupPrinters = new Group("Printers", categoryPrintersScanners);

        Product productNotebookBasic15 = new Product();
            productNotebookBasic15.setGroup(groupLaptops);
            productNotebookBasic15.setDescriptionFull("Notebook Basic 15 with 2,80 GHz quad core, 15 LCD, 4 GB DDR3 RAM, 500 GB Hard Disc, Windows 8 Pro");
            productNotebookBasic15.setPicture("Notebook Basic 15.jpg");
            productNotebookBasic15.setStoreStatus(true);
            productNotebookBasic15.setPrice(956);
//            productNotebookBasic15.setDiscount(0);
            productNotebookBasic15.setDescriptionPreview("Notebook Basic 15 with 2,80 GHz quad core, 15 LCD");
            productNotebookBasic15.setName("Notebook Basic 15");

        Product productNotebookBasic17 = new Product();
            productNotebookBasic17.setGroup(groupLaptops);
            productNotebookBasic17.setDescriptionFull("Notebook Basic 17 with 2,80 GHz quad core, 17 LCD, 4 GB DDR3 RAM, 500 GB Hard Disc, Windows 8 Pro");
            productNotebookBasic17.setPicture("Notebook Basic 17.jpg");
            productNotebookBasic17.setStoreStatus(true);
            productNotebookBasic17.setPrice(1249);
//            productNotebookBasic17.setDiscount(0);
            productNotebookBasic17.setDescriptionPreview("Notebook Basic 17 with 2,80 GHz quad core, 17 LCD");
            productNotebookBasic17.setName("Notebook Basic 17");

        Product productNotebookBasic18 = new Product();
            productNotebookBasic18.setGroup(groupLaptops);
            productNotebookBasic18.setDescriptionFull("Notebook Basic 18 with 2,80 GHz quad core, 18 LCD, 8 GB DDR3 RAM, 1000 GB Hard Disc, Windows 8 Pro");
            productNotebookBasic18.setPicture("Notebook Basic 18.jpg");
            productNotebookBasic18.setStoreStatus(false);
            productNotebookBasic18.setPrice(1570);
//            productNotebookBasic18.setDiscount(0);
            productNotebookBasic18.setDescriptionPreview("Notebook Basic 18 with 2,80 GHz quad core, 18 LCD");
            productNotebookBasic18.setName("Notebook Basic 18");

        Product productPC500 = new Product();
            productPC500.setGroup(groupPCs);
            productPC500.setDescriptionFull("PC-500, 128Gb SSD, 4Gb RAM, i3+GTX710, PC-500 - лучший выбор для офиса");
            productPC500.setPicture("PC-500.jpg");
            productPC500.setStoreStatus(false);
            productPC500.setPrice(470);
//            productPC500.setDiscount(0);
            productPC500.setDescriptionPreview("PC-500, 128Gb SSD, 4Gb RAM, i3+GTX710");
            productPC500.setName("PC-500");

        Product productPC1000 = new Product();
            productPC1000.setGroup(groupPCs);
            productPC1000.setDescriptionFull("PC 1000, 128Gb SSD, 8Gb RAM, i3+GTX760, PC-1000 - лучший выбор для школьника (азаза)");
            productPC1000.setPicture("PC-1000.jpg");
            productPC1000.setStoreStatus(true);
            productPC1000.setPrice(999);
            productPC1000.setDiscount(3);
            productPC1000.setDescriptionPreview("PC 1000, 128Gb SSD, 8Gb RAM, i5+GTX760");
            productPC1000.setName("PC-1000");

        Product productPC1500 = new Product();
            productPC1500.setGroup(groupPCs);
            productPC1500.setDescriptionFull("PC-1500, 256Gb SSD, 16Gb RAM, i7+GTX1080Ti, PC-1500 - лучший выбор для олдов (они здесь?)");
            productPC1500.setPicture("PC-1500.jpg");
            productPC1500.setStoreStatus(false);
            productPC1500.setPrice(1649);
            productPC1500.setDiscount(5);
            productPC1500.setDescriptionPreview("PC 1000, 256Gb SSD, 16Gb RAM, i7+GTX1080Ti");
            productPC1500.setName("PC-1500");

        Product productErgonomicKeyboard = new Product();
            productErgonomicKeyboard.setGroup(groupKeyboards);
            productErgonomicKeyboard.setDescriptionFull("Ergonomic USB Keyboard for Desktop, Plug&Play, идеально подойдёт нищебродам");
            productErgonomicKeyboard.setPicture("Ergonomic Keyboard.jpg");
            productErgonomicKeyboard.setStoreStatus(true);
            productErgonomicKeyboard.setPrice(14);
//            productErgonomicKeyboard.setDiscount(0);
            productErgonomicKeyboard.setDescriptionPreview("Ergonomic USB Keyboard for Desktop, Plug&Play");
            productErgonomicKeyboard.setName("Ergonomic Keyboard");

        Product productInternetKeyboard = new Product();
            productInternetKeyboard.setGroup(groupKeyboards);
            productInternetKeyboard.setDescriptionFull("Corded Keyboard with special keys for Internet Usability, USB, идеально подойдёт офисным планктонам");
            productInternetKeyboard.setPicture("Internet Keyboard.jpg");
            productInternetKeyboard.setStoreStatus(true);
            productInternetKeyboard.setPrice(16);
//            productInternetKeyboard.setDiscount(0);
            productInternetKeyboard.setDescriptionPreview("Corded Keyboard with special keys for Internet Usability, USB");
            productInternetKeyboard.setName("Internet Keyboard");

        Product productMediaKeyboard = new Product();
            productMediaKeyboard.setGroup(groupKeyboards);
            productMediaKeyboard.setDescriptionFull("Corded Ergonomic Keyboard with special keys for Media Usability, USB, идеально подойдёт медиа-личностям (WTF?)");
            productMediaKeyboard.setPicture("Media Keyboard.jpg");
            productMediaKeyboard.setStoreStatus(true);
            productMediaKeyboard.setPrice(26);
//            productMediaKeyboard.setDiscount(0);
            productMediaKeyboard.setDescriptionPreview("Corded Keyboard with special keys for Media Usability, USB");
            productMediaKeyboard.setName("Media Keyboard");

        Product productGTX590 = new Product();
            productGTX590.setGroup(groupGraphicsCard);
            productGTX590.setDescriptionFull("PCI-E GDDR5 3072MB DVI Out, TV Out low-noise, раритет!");
            productGTX590.setPicture("GTX-590.jpg");
            productGTX590.setStoreStatus(true);
            productGTX590.setPrice(200);
//            productGTX590.setDiscount(0);
            productGTX590.setDescriptionPreview("PCI-E GDDR5 3072MB, DVI");
            productGTX590.setName("GTX-590");

        Product productGTX1080Ti = new Product();
            productGTX1080Ti.setGroup(groupGraphicsCard);
            productGTX1080Ti.setDescriptionFull("PCI-E GDDR5 8072MB DVI Out, TV Out low-noise, HDMI, Ultra Game Settings ");
            productGTX1080Ti.setPicture("GTX-1080Ti.jpg");
            productGTX1080Ti.setStoreStatus(true);
            productGTX1080Ti.setPrice(489);
//            productGTX1080Ti.setDiscount(0);
            productGTX1080Ti.setDescriptionPreview("PCI-E GDDR5 8072MB, DVI, HDMI");
            productGTX1080Ti.setName("GTX-1080Ti");

        Product productRussianVideocard = new Product();
            productRussianVideocard.setGroup(groupGraphicsCard);
            productRussianVideocard.setDescriptionFull("Русское полное описание");
            productRussianVideocard.setPicture("RussianVideocard.jpg");
            productRussianVideocard.setStoreStatus(true);
            productRussianVideocard.setPrice(489);
//            productRussianVideocard.setDiscount(0);
            productRussianVideocard.setDescriptionPreview("Русиш аписанне");
            productRussianVideocard.setName("Рузкая видевакарта");

        Product productLaserProfessionalEco = new Product();
            productLaserProfessionalEco.setGroup(groupPrinters);
            productLaserProfessionalEco.setDescriptionFull("Print 2400 dpi image quality color documents at speeds of up to 32 ppm (color) or 36 ppm (monochrome), letter/A4. Powerful 500 MHz processor, 512MB of memory");
            productLaserProfessionalEco.setPicture("Laser Professional Eco.jpg");
            productLaserProfessionalEco.setStoreStatus(true);
            productLaserProfessionalEco.setPrice(830);
//            productLaserProfessionalEco.setDiscount(0);
            productLaserProfessionalEco.setDescriptionPreview("Print 2400 dpi image quality color documents");
            productLaserProfessionalEco.setName("Laser Professional Eco");

        Product productUltraJetSuperColor = new Product();
            productUltraJetSuperColor.setGroup(groupPrinters);
            productUltraJetSuperColor.setDescriptionFull("4800 dpi x 1200 dpi - up to 35 ppm (mono) / up to 34 ppm (color) - capacity: 250 sheets - Hi-Speed USB, Ethernet");
            productUltraJetSuperColor.setPicture("Ultra Jet Super Color.jpg");
            productUltraJetSuperColor.setStoreStatus(true);
            productUltraJetSuperColor.setPrice(139);
//            productUltraJetSuperColor.setDiscount(0);
            productUltraJetSuperColor.setDescriptionPreview("4800 dpi x 1200 dpi - up to 35ppm/34ppm (color)");
            productUltraJetSuperColor.setName("Ultra Jet Super Color");

        categoryRepository.save(categoryComputerSystems);
            groupLaptops.setCategory(categoryRepository.findByName(categoryComputerSystems.getName()));
            groupRepository.save(groupLaptops);
                productRepository.save(productNotebookBasic15);
                productRepository.save(productNotebookBasic17);
                productRepository.save(productNotebookBasic18);
            groupPCs.setCategory(categoryRepository.findByName(categoryComputerSystems.getName()));
            groupRepository.save(groupPCs);
                productRepository.save(productPC500);
                productRepository.save(productPC1000);
                productRepository.save(productPC1500);
        categoryRepository.save(categoryComputerComponents);
            groupGraphicsCard.setCategory(categoryRepository.findByName(categoryComputerComponents.getName()));
            groupRepository.save(groupGraphicsCard);
                productRepository.save(productGTX590);
                productRepository.save(productGTX1080Ti);
                productRepository.save(productRussianVideocard);
            groupKeyboards.setCategory(categoryRepository.findByName(categoryComputerComponents.getName()));
            groupRepository.save(groupKeyboards);
                productRepository.save(productInternetKeyboard);
                productRepository.save(productErgonomicKeyboard);
                productRepository.save(productMediaKeyboard);
        categoryRepository.save(categoryPrintersScanners);
            groupPrinters.setCategory(categoryRepository.findByName(categoryPrintersScanners.getName()));
            groupRepository.save(groupPrinters);
                productRepository.save(productLaserProfessionalEco);
                productRepository.save(productUltraJetSuperColor);
    }
}

