package com.example.nagoyameshi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.nagoyameshi.entity.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {
	public Restaurant findRestaurantById(Integer id);
	
	public Page<Restaurant> findByNameLike(String keyword, Pageable pageable);
	
	public Page<Restaurant> findAllByOrderByCreatedAtDesc(Pageable pageable);
	
	public Page<Restaurant> findAllByOrderByLowestPriceAsc(Pageable pageable);
	
	@Query("SELECT r FROM Restaurant r " +
           "LEFT JOIN r.reviews rev " +
           "GROUP BY r.id " +
           "ORDER BY AVG(rev.score) DESC")
	public Page<Restaurant> findAllByOrderByAverageScoreDesc(Pageable pageable);
	
	
	@Query("SELECT DISTINCT r FROM Restaurant r " +
			"LEFT JOIN r.categoriesRestaurants cr " +
			"WHERE r.name LIKE %:name% " +
			"OR r.address LIKE %:address% " +
			"OR cr.category.name LIKE %:categoryName% " +
			"ORDER BY r.createdAt DESC")
	public Page<Restaurant> findByNameLikeOrAddressLikeOrCategoryNameLikeOrderByCreatedAtDesc(@Param("name") String nameKeyword,
																							  @Param("address") String addressKeyword,
																							  @Param("categoryName") String categoryNameKeyword,
																							  Pageable pageable);
	
	@Query("SELECT DISTINCT r FROM Restaurant r " +
	           "LEFT JOIN r.categoriesRestaurants cr " +
	           "WHERE r.name LIKE %:name% " +
	           "OR r.address LIKE %:address% " +
	           "OR cr.category.name LIKE %:categoryName% " +
	           "ORDER BY r.lowestPrice ASC")
	public Page<Restaurant> findByNameLikeOrAddressLikeOrCategoryNameLikeOrderByLowestPriceAsc(@Param("name") String nameKeyword,
																							  @Param("address") String addressKeyword,
																							  @Param("categoryName") String categoryNameKeyword,
																							  Pageable pageable);
	
	@Query("SELECT r FROM Restaurant r " +
           "LEFT JOIN r.categoriesRestaurants cr " +
           "LEFT JOIN r.reviews rev " +
           "WHERE r.name LIKE %:name% " +
           "OR r.address LIKE %:address% " +
           "OR cr.category.name LIKE %:categoryName% " +
           "GROUP BY r.id " +
           "ORDER BY AVG(rev.score) DESC")
	public Page<Restaurant> findByNameLikeOrAddressLikeOrCategoryNameLikeOrderByAverageScoreDesc(@Param("name") String nameKeyword,
																								 @Param("address") String addressKeyword,
																								 @Param("categoryName") String categoryNameKeyword,
																								 Pageable pageable);
	
	@Query("SELECT r FROM Restaurant r " +
	           "INNER JOIN r.categoriesRestaurants cr " +
	           "WHERE cr.category.id = :categoryId " +
	           "ORDER BY r.createdAt DESC")
	public Page<Restaurant> findByCategoryIdOrderByCreatedAtDesc(@Param("categoryId") Integer categoryId, Pageable pageable);
	
	@Query("SELECT r FROM Restaurant r " +
	           "INNER JOIN r.categoriesRestaurants cr " +
	           "WHERE cr.category.id = :categoryId " +
	           "ORDER BY r.lowestPrice ASC")
	public Page<Restaurant> findByCategoryIdOrderByLowestPriceAsc(@Param("categoryId") Integer categoryId, Pageable pageable);
	
	 @Query("SELECT r FROM Restaurant r " +
           "INNER JOIN r.categoriesRestaurants cr " +
           "LEFT JOIN r.reviews rev " +
           "WHERE cr.category.id = :categoryId " +
           "GROUP BY r.id " +
           "ORDER BY AVG(rev.score) DESC")
	public Page<Restaurant> findByCategoryIdOrderByAverageScoreDesc(@Param("categoryId") Integer categoryId, Pageable pageable);
	
	public Page<Restaurant> findByLowestPriceLessThanEqualOrderByCreatedAtDesc(Integer price, Pageable pageable);
	public Page<Restaurant> findByLowestPriceLessThanEqualOrderByLowestPriceAsc(Integer price, Pageable pageable); 
	
	@Query("SELECT r FROM Restaurant r " +
	       "LEFT JOIN r.reviews rev " +
           "WHERE r.lowestPrice <= :price " +
           "GROUP BY r.id " +
           "ORDER BY AVG(rev.score) DESC")
	public Page<Restaurant> findByLowestPriceLessThanEqualOrderByAverageScoreDesc(@Param("price") Integer price, Pageable pageable);
}
