package models;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order {

    @Id
    @SequenceGenerator(name = "my_seq", sequenceName = "seq1", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_seq")
    private Long id;

    @Column(name = "order_number")
    private String orderNumber;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "order_rows", joinColumns = @JoinColumn(name = "orders_id"))
    private List<OrderRow> orderRows;
}