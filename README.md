# ProyectoJavaWebServletsMVC
Este fué mi proyecto de Programación avanzada de la Maestría en Ciencia e Ingeniería de la Computación en la UNAM.
Es un proyecto Web hecho en lenguaje Java con el IDE Netbeans, para abrir el proyecto se debe importar el .zip desde Netbeans.

Los algoritmos consisten en problemas con teoría de Gráficos, Depth First Search y encotrar un ciclo Hamiltoniano.

En el proyecto se utilizan estas estructuras de grafos. Grafo.java es nuestra clase principal la cual tiene los siguientes atributos:

Nombre, Número de vértices, Número de aristas, Lista de adyacencias, Matriz de adyacencias.

Para crear un objeto grafo se requieren pasar los siguientes datos al constructor: nombre,
número de vértices y número de aristas.

La base de datos se creó con MySQL, los queries utilizados se incluyen en el repositorio también.

La base de datos contiene guardados varios grafos de ejemplos. El proyecto lee la base de datos y codifica la forma en que esta guardado en la tabla para poder obtener los atributos y
crear los objetos grafos.

La lista de adyacencias que aparece al leer todos los grafos en la Base de Datos indica los vértices conectados en cada vértice, por ejemplo, si la lista de adyacencias es:
[[1, 2], [2], [0, 3], [3]] eso quiere decir que:
-El vértice 0 se conecta con 1 y con 2.
-El vértice 1 se conecta con 2.
-El vértice 2 se conecta con 0 y con 3.
-Y el vértice 3 se conecta con sí mismo.


La búsqueda en profundidad (DFS) es un algoritmo que se utiliza para visitar todos los vértices en un grafo.

Al final lo que obtenemos de este algoritmo es una lista con los vértices del grafo en el orden en el que fueron visitados. Para el proyecto se definió obtener el DFS siempre desde el vértice 0.

Un ciclo Hamiltoniano es un ciclo en un grafo en el cual se recorre todos los vértices
exactamente una vez nada más.

Para obtener resultados de este algoritmo se le debe proporcionar el nombre del grafo y el vértice inicial.
Al final se obtiene un String que dice si el grafo tiene o no ciclo Hamiltoniano, y si tiene imprime el ciclo iniciando y terminando en el vértice que se proporcionó.

Nota final: Cuando se cierra la ventana de la imagen del grafo solo se esconde ya que se tiene comentado la parte de app.setDefaultCloseOperation y por default usa la opción
HIDE_ON_CLOSE.
