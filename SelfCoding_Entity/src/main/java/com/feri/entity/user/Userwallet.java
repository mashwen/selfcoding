package com.feri.entity.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author Feri
 * @since 2019-03-20
 */
@TableName("userwallet")
public class Userwallet extends Model<Userwallet> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
	private Integer coins;
	private Integer redcoins;
	private Integer losecoins;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCoins() {
		return coins;
	}

	public void setCoins(Integer coins) {
		this.coins = coins;
	}

	public Integer getRedcoins() {
		return redcoins;
	}

	public void setRedcoins(Integer redcoins) {
		this.redcoins = redcoins;
	}

	public Integer getLosecoins() {
		return losecoins;
	}

	public void setLosecoins(Integer losecoins) {
		this.losecoins = losecoins;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
