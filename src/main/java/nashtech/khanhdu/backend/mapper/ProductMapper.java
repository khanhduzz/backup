package nashtech.khanhdu.backend.mapper;

import nashtech.khanhdu.backend.dto.ProductDto;
import nashtech.khanhdu.backend.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(target = "categories", ignore = true)
    ProductDto toDto (Product product);

    @Mapping(target = "categories", ignore = true)
    @Mapping(target = "usersRating", ignore = true)
    @Mapping(target = "orders", ignore = true)
    Product toEntity (ProductDto dto);

    @Mapping(target = "categories", ignore = true)
    @Mapping(target = "usersRating", ignore = true)
    @Mapping(target = "orders", ignore = true)
    Product updateProduct (@MappingTarget Product product, ProductDto dto);
}
