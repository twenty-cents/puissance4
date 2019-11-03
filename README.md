# Jeu de puissance 4
Simplon - Brief 4 - Jeu de puissance 4

# Description
Ce jeu se joue à deux joueurs sur une grille de hauteur 6 et de largeur 7 (voir par exemple https://fr.wikipedia.org/wiki/Puissance₄). Les joueurs laissent tomber chacun leur tour un jeton dans une colonne. Lorsqu’un joueur aligne 4 jetons de sa couleur (en ligne ou en diagonale), il a gagné. Il peut arriver qu’aucun joueur ne parvienne à aligner 4 jetons, donnant lieu à un match nul.

# Implémentation
Le déroulement du jeu est géré dans la classe Puissance4, qui utilise une table de 7 colonnes x 6 lignes pour modéliser la grille de jeu.
Chaque case est représentée par un objet Token, ce qui permet d'en extraire facilement son contenu (vide, jeton rouge ou jaune).

Les affichages dans la console sont gérés dans la classe Terminal.

Il est possible de jouer contre l'ordinateur (ou de faire jouer l'ordinateur contre lui-même).
Ce dernier joue selon une stratégie naive (la colonne est choisie au hasard).

# Notice d'utilisation
Avant le début de la partie, les informations suivantes sont demandées pour chaque joueur (2 maximum) :
- Nom du joueur : champs de saisie libre non vide.
- BOT ou HUMAN : joueur humain (HUMAN) ou ordinateur (BOT).

Une fois ces informations saisies, la partie peut commencer. 
A chaque tour, un joueur saisie le numéro de la colonne désiré.
Le jeu se termine si quatre jetons consécutifs d'une même couleur sont alignés (horizontalement, verticalement ou dans n'importe quelle diagonale).
  

# Exemple d'utilisation

>  [        ][        ][        ][        ][        ][        ][        ]

>  [        ][  RED   ][        ][        ][        ][        ][        ]

>  [ YELLOW ][ YELLOW ][        ][ YELLOW ][        ][ YELLOW ][        ]

>  [  RED   ][  RED   ][  RED   ][ YELLOW ][        ][ YELLOW ][        ]

>  [  RED   ][  RED   ][ YELLOW ][  RED   ][  RED   ][ YELLOW ][        ]

>  [  RED   ][ YELLOW ][  RED   ][ YELLOW ][  RED   ][ YELLOW ][ YELLOW ]

>  SKYNET (YELLOW) a gagné la partie !

>  

>  Voulez-vous faire une autre partie (O/N) ? : 