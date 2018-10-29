package handson.example.springshopsearch.model.item;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data

public class Item {
		//主キー
	    @Id
	    //自動でキーを設定してくれる
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    //カラムを意味するもの
	    @Column(name = "id")
	    public Long id;

	    @Column(name = "name")
	    public String name;
	    
	    //@minで最小値0を定義
	    @Min(value = 0)
	    @Column(name = "price")
	    public int price;

	    @Column(name = "description", columnDefinition = "TEXT")
	    public String description;
	
}
