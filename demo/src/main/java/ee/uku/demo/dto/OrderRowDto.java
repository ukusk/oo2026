package ee.uku.demo.dto;

public record OrderRowDto( // DTO --> Data Transfer Object
                           Long productId,
                           int quantity
) {
}
