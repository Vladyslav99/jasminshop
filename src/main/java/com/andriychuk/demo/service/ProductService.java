package com.andriychuk.demo.service;

import com.andriychuk.demo.entity.Product;
import com.andriychuk.demo.enums.ProductType;
import com.andriychuk.demo.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public void delete(Product product) {
        productRepository.delete(product);
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No such item"));
    }

    @Transactional
    @PostConstruct
    public void startProducts() {
        if (productRepository.findAll().isEmpty()) {
            /* UNIVERSAL ITEMS */
            productRepository.save(new Product(1L, "Білизна анти бак 5л засіб для миття та дезінфекції поверхонь", ProductType.UNIVERSAL,
                    "Концентрований засіб для миття всіх видів поверхонь (стін, підвіконь, статі, жорсткої меблів, " +
                            "обідніх столів, журнальних столиків, меблів та їх твердих частин, робочих поверхонь, " +
                            "обладнання і т. д.) у закладах охорони здоров'я різного профілю, на підприємствах фармацевтичної, " +
                            "хімічної біотехнологічної, мікробіологічної, харчової та переробної промисловості, підприємствах " +
                            "комунального господарства, громадського харчування і торгівлі, на всіх видах транспорту, в " +
                            "навчальних та дошкільних закладах, побуті та інших закладах де потрібно дотримуватися належної " +
                            "гігієни.", "https://images.ua.prom.st/2528884483_w700_h500_bilizna-anti-bak.jpg", BigDecimal.valueOf(298)));

            productRepository.save(new Product(2L, "Універсальний чистячий засіб Karcher RM 555, 5л", ProductType.UNIVERSAL,
                    "Засіб універсального призначення для видалення масляних, жирових та мінеральних забруднень. " +
                            "Для очищення садових меблів, автомобілів, фасадів та будь-яких водостійких поверхонь.",
                    "https://images.ua.prom.st/3068513558_w640_h640_universalnij-chistyachij-zasib.jpg", BigDecimal.valueOf(399)));

            productRepository.save(new Product(3L, "GRAN QAT Дезінфікуючий засіб , універсальне, без запаху 5л TENZI",
                    ProductType.UNIVERSAL, "Gran Qat — дезінфікуючий засіб, універсальне, без запаху. " +
                    "Засіб для миття та загальної дезінфекції поверхонь різного типу і обладнання приміщень на підприємствах " +
                    "громадського харчування. Рекомендується для кухонних меблів, стійок, прилавків, столів, полиць, кас, " +
                    "скляних поверхонь, холодильників. Немає запаху.", "https://images.ua.prom.st/2940997925_w700_h500_gran-qat-.jpg",
                    BigDecimal.valueOf(760)));

            productRepository.save(new Product(4L, "Миючий засіб для післябудівного прибирання, концентрат MK мп (1.1 кг)",
                    ProductType.UNIVERSAL, "PRIMA MK м/п - це концентроване малопенное кислотний засіб для після " +
                    "будівельного клінінгу. Призначено для догляду і глибокого чищення кислотостійких поверхонь. " +
                    "Використовується для автоматичної мийки (роторні машини). Швидко та ефективно видаляє забруднення.",
                    "https://images.ua.prom.st/2708544152_w700_h500_miyuchij-zasib-dlya.jpg", BigDecimal.valueOf(122)));

            productRepository.save(new Product(5L, "Білизна поверхня, білизна поверхню універсальний миючий засіб, 5 л",
                    ProductType.UNIVERSAL, "Концентрований засіб для миття всіх видів поверхонь (стін, підвіконь, " +
                    "підлоги, твердих меблів, обідніх столів, журнальних столиків, інших меблів та їх твердих частин, робочих " +
                    "поверхонь, устаткування тощо) у закладах охорони здоров'я різного профілю, на підприємствах фармацевтичної," +
                    " хімічної, біотехнологічної, мікробіологічної, харчової та переробної промисловості, підприємствах комунального" +
                    " господарства, громадського харчування і торгівлі, на всіх видах транспорту, в учбових та дошкільних закладах," +
                    " побуті та в інших закладах де потрібно дотримуватися належної гігієни.",
                    "https://images.ua.prom.st/2504219413_w700_h500_bilizna-poverhnya-bilizna.jpg", BigDecimal.valueOf(160)));

            /* ---------------------------------------------------------- */

            /* FLOOR ITEMS */
            productRepository.save(new Product(6L, "Засіб для миття підлоги, плитки, дерева, ПВХ, лінолеуму TENZI TopEfekt Normal 5л",
                    ProductType.FLOOR_CLEANERS, "Засіб для миття підлоги, плитки, дерева, ПВХ, лінолеуму TopEfekt Normal. " +
                    "Концентрований, малопенящийся препарат для миття плитки керамічної, каменю, ПВХ, лакированого дерева, лінолеуму. " +
                    "Зберігає натуральне властивість підлоги, не залишає розводів. Має приємний запах лимона. Запобігає повторне проникнення" +
                    " бруду. Препарат для щоденного миття часто загрязняющихся підлоги.",
                    "https://images.ua.prom.st/2357519779_w640_h640_zasib-dlya-mittya.jpg", BigDecimal.valueOf(450)));

            productRepository.save(new Product(7L, "ДЕЗІНФЕКТОР 5 л. ЗАСІБ ДЛЯ МИТТЯ ПОВЕРХОНЬ І ПІДЛОГ",
                    ProductType.FLOOR_CLEANERS, "Засіб для миття підлоги та інших поверхонь з дезінфекційними властивостями " +
                    "ТМ «Brilias» - забезпечує легке видалення забруднень різного характеру і знищення бактеріальних інфекцій, вірусів," +
                    " грибкових і паразитарних збудників хвороб. Володіє високим показником очищувальних і знежирювальних властивостей." +
                    " Легко розчинний у воді в різних співвідношеннях.",
                    "https://images.ua.prom.st/2313061606_w640_h640_dezinfektor-5-l.jpg", BigDecimal.valueOf(465)));

            productRepository.save(new Product(8L, "Засіб для миття підлоги BLITZ Універсальний 1000 гр.",
                    ProductType.FLOOR_CLEANERS, "Ефективний засіб, призначений для систематичного миття підлогових " +
                    "покриттів різних видів. Швидко змиває побутові забруднення, поліпшує зовнішній вигляд і подовжує термін " +
                    "експлуатації поверхонь. Містить у своєму складі компонент, має бактерицидну дію відносно грампозитивних і" +
                    " грамнегативих бактерій, грибків і цвілі. Не залишає розлучень після висихання.",
                    "https://images.ua.prom.st/2680745136_w700_h500_zasib-dlya-mittya.jpg", BigDecimal.valueOf(26)));


            productRepository.save(new Product(9L, "Засіб для миття підлоги BLITZ Desinfection 1000 гр.",
                    ProductType.FLOOR_CLEANERS, "Ефективний засіб, призначений для систематичного миття підлогових " +
                    "покриттів різних видів. Швидко змиває побутові забруднення, поліпшує зовнішній вигляд і подовжує термін" +
                    " експлуатації поверхонь. Містить у своєму складі компонент, має бактерицидну дію відносно грампозитивних і" +
                    " грамнегативих бактерій, грибків і цвілі. Не залишає розлучень після висихання.",
                    "https://images.ua.prom.st/2681147334_w700_h500_zasib-dlya-mittya.jpg", BigDecimal.valueOf(29)));


            productRepository.save(new Product(10L, "Засіб для миття жирових забруднень (антіжір для поверхонь), без запаху TENZI Gran Bis 1л (концентрат)",
                    ProductType.FLOOR_CLEANERS, "Засіб для миття жирових забруднень, без запаху Gran Bis. " +
                    "Концентрований высокощелочной препарат без запаху для миття сильних жирових забруднень. Усуває " +
                    "сильні забруднення тваринного і рослинного походження; володіє високою здатністю емульгації жирів;" +
                    " містить інгібітори корозії; не містить фосфатів. Рекомендований до пінному методом миття.",
                    "https://images.ua.prom.st/2359353983_w640_h640_zasib-dlya-mittya.jpg", BigDecimal.valueOf(126)));

            /* ---------------------------------------------------------- */

            /* CHEMICAL FERTILIZERS */
            productRepository.save(new Product(11L, "Азотна кислота Ч, 5 літрів, MendeleevMarket",
                    ProductType.CHEMICAL_FERTILIZERS, "MendeleevMarket™ пропонує азотну кислоту з кваліфікацією Ч (чиста). " +
                    "Азотна кислота, яку ви купуєте в хімічному маркеті MendeleevMarket™, має масову частку основної речовини 57%. " +
                    "Формула азотної кислоти - HNO3. Азотну кислоту використовують як добриво в сільському господарстві, в хімічній " +
                    "промисловості, а також для аффінажу дорогоцінних металів. ",
                    "https://images.ua.prom.st/3081496841_w700_h500_azotna-kislota-ch.jpg", BigDecimal.valueOf(250)));

            productRepository.save(new Product(12L, "Сода Каустична Сода 1кг Їдкий Натр Луг Гранула",
                    ProductType.CHEMICAL_FERTILIZERS, "Каустична сода - сильний отруйний луг. Якщо його  розчин" +
                    " потрапить на шкіру, то можуть виникнути опіки, виразки. Каустична сода належить до 2 класу небезпеки, " +
                    "тому від час використання необхідно дотримуватися запобіжних заходів",
                    "https://images.ua.prom.st/3129276802_w640_h640_soda-kaustichna-soda.jpg", BigDecimal.valueOf(95)));

            productRepository.save(new Product(13L, "Бензол \"ЧДА\" 99,9% 1л",
                    ProductType.CHEMICAL_FERTILIZERS, "Бензол (бензен) - безбарвна, прозора рідина з характерним " +
                    "ароматичним запахом. добре змішується з ефіром, бензином та іншими органічними розчинниками, з водою " +
                    "утворює азеотропну суміш з температурою кипіння 69,25 °C.",
                    "https://images.ua.prom.st/3126293896_w640_h640_benzol-chda-999.jpg", BigDecimal.valueOf(350)));


            productRepository.save(new Product(14L, "Еколайн Залізо (Хелат)",
                    ProductType.CHEMICAL_FERTILIZERS, "Еколайн Залізо Хелат - висококонцентроване хелатоване " +
                    "добриво для позакореневого підживлення розроблене для усунення прояву дефіциту заліза, та для культур" +
                    " особливо вимогливих до умов забезпечення залізом.",
                    "https://zernina.com.ua/15397-large_default/ekolajn-zalizo-khelat.jpg", BigDecimal.valueOf(2373)));


            productRepository.save(new Product(15L, "Каустична сода 25 кг/мішок Гранула, Їдкий Натр",
                    ProductType.CHEMICAL_FERTILIZERS, "Каустична сода - сильний отруйний луг. Якщо його " +
                    "розчин потрапить на шкіру, то можуть виникнути опіки, виразки. Каустична сода належить до 2 класу небезпеки," +
                    " тому від час використання необхідно дотримуватися запобіжних заходів",
                    "https://images.ua.prom.st/3128971177_w640_h640_kaustichna-soda-25.jpg", BigDecimal.valueOf(1200)));

        }
    }
}
