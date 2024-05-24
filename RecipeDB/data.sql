INSERT INTO recipe (recipe_name, description, prep_time, cook_time, total_time, servings, categories_id, child_categories_name)
VALUES 
('Tacos', 'Mexican Cuisine with meat, shells and cheese', 15, 10, 25, 2, 1, 'Beef'),
('Cheesy Chicken Rice Casserole', 'A rice casserole  with shreadded chicken and melty cheese', 20, 30, 60, 8, 1, 'Chicken');

INSERT INTO ingrediants(ingrediant_name, description)
VALUES
('Ground Beef', '80% lean ground beef'),
('Roma Tomato', 'A plum tomato with few seeds'),
('Shreadded Lettuce', '1 bag of shreaded lettuce'),
('Sour Creme', 'Creamy texture, tart taste'),
('Taco Sauce', 'Mild to hot sauce'),
('Shreaded Cheddar Cheese', '1 Bag of cheddar cheese'),
('Taco Seasoning', 'Seasoning for tacos'),
('Pepper', 'black pepper');

INSERT INTO categories (categories_name)
VALUES
('Entree'),
('Side'),
('Dessert');
-- define Entree/ beef ect

INSERT INTO recipe_ingrediants (recipe_id, ingrediant_id)
VALUES 
(1,1),
(1,2),
(1,3),
(1,4),
(1,5),
(1,6),
(1,7);


INSERT INTO recipe_categories (recipe_id, child_categories_id)
VALUES
(1,1),
(2,2);



SELECT * FROM recipe
INNER JOIN categories ON recipe.recipe_id = 1;