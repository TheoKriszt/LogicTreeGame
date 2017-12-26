# LogicTreeGame
## Jeu de logique booléenne

Petit jeu simple qui présente des formules logiques au joueur du type 
NOT (P => ((P => Q) => Q))

Quand le joueur clique sur une formule booléenne, le jeu la simplifie si possible en 
un ensemble de propositions conjonctives.
En créant ainsi des branches de résolution de la formule de départ, le joueur doit repérer 
les fermetures (les contradictions) de chaque branche ainsi créée (ex : P et NOT P) au sein de la même branche.

Le joueur gagne la partie quand toutes les branches ont été fermées.
Le jeu rapelle alors au joueur combien de coups ont été nécessaires à la résolution de la formule.

Attention, il existe pour les formules les plus complexes des solutions plus courtes que d'autres !
