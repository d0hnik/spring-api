package models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static org.hsqldb.lib.tar.TarHeaderField.name;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
@Table(name = "order_rows")
public class OrderRow {

    @Column(name = "item_name")
    private String itemName;

    @NotNull(message="Quantity must not be null")
    @Min(value = 1, message = "Quantity must be at least 1")
    private Integer quantity;

    @NotNull
    @Min(value = 1, message = "Price must be at least 1")
    private Integer price;

}