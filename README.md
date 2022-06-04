                                            PRÁCTICA 1: SISTEMAS INTELIGENTES
Autores --> Miguel López // Xoel Díaz

# Ejercicio 1
## 1.a)
En este ejercicio creamos la clase *Nodo*,cuyos objetos disponen de 3 campos, (padre,estado y acción), así como su constructor y
el método toString.<br/>
En la clase Estrategia4, modificamos el método soluciona para que devuelva un Nodo[], así como creamos otro método para devolver la lista de nodos recorridos hasta llegar a la solución (reconstruye_sol).<br/>
reconstruye_sol se sitúa en el nodo que recibe como parámetro y va añadiendo nodos y acto seguido actualizándose al padre del nodo hasta que llegue al nodo null.
## 1.b)
En este apartado, implementamos la clase EstrategiaBusquedaGrafo, tomando como plantilla Estrategia4, ya que con esta implementación, no existe el problema en el que en algunos casos no encontraba solución al llegar a un estado sin sucesores. Esto lo solucionamos mediante la inclusión de una estructura LIFO (pila)
para poder realizar un recorrido en profundidad y guardar los estados no explorados en esta frontera LIFO, a la cual recurriremos cuando no queden sucesores, extrayendo de ella el último elemento en ser insertado. Mediante la inclusión de un bucle infinito y una comprobación de la no vacuidad de la frontera, así como también comprobamos que el estado actual no es meta. Cuando avanzamos a un estado nuevo, lo añadimos a nuestro ArrayList de estados llamado explorados.<br/> El siguiente paso es calcular todos los posibles sucesores, para lo cual usamos un bucle con todas nuestras acciones disponibles y mediante una varibale local de tipo *Nodo* creamos un nodo por cada sucesor. Este nodo y su respectivo estado serán usados para la siguiente comprobación, en la cual añadimos el nodo a la frontera si su estado no ha sido explorado ya.<br/> Por último, llamamos a reconstruye_sol(nodoActual).<br/>

# Ejercicio 2
## 2.a)
En este ejercicio empezamos realizando la creación de la clase ProblemaCuadradoMagico, la cual en si misma tiene EstadoCuadrado y AccionCuadrado, subclases de Estado y Accion respectivamente, las cuales nos van a permitir comprobar si una acción es aplicable (una acción es aplicable si el valor de la matriz en esa coordenada es 0 (vacía en esa coordenada)), calcular el resultado de aplicar una acción concreta a un estado concreto y también tenemos nuestra lista de acciones, y nuestro método acciones, el cual devuelve un array de las posibles acciones aplicables al estado recibido por parámetro, y el último método, esMeta, comprueba si un estado determinado es solución de nuestro problema del cuadrado mágico, mediante la suma de filas, columnas y diagonales.<br/>
Para la implementación de la búsqueda en anchura reutilizamos el código del ejercicio 1, usando una cola y seleccionando siempre el primer nodo disponible (head), y para la implementación de la búsqueda por profundidad usamos un tipo Stack, ya que así avanzamos por el último nodo introducido a nuestra frontera.<br/>
Para llevar la cuenta de cuantos nodos creados y expandidos llevamos, creamos una variable que aumentamos en nuestro bucle cada vez que creamos un nodo sucesor, mientras que para llevar la cuenta de cuantos expandidos, simplemente mostramos el .size de nuestro arrayList explorados.<br/>
La estrategia más adecuada es la búsqueda en profundidad, ya que la búsqueda en anchura recorre todos los posibles nodos, llevando esto a altos tiempos de ejecución, mientras que en profundidad no necesita recorrer todo el grafo a menos que sea el peor caso.<br/>
## 2.b)
En este apartado, para implementar la función heurística hicimos unos pequeños cambios en nuestra clase *Nodo*, añadiendo dos parámetros, el coste del camino y el coste de la función *f*, siendo la *f* la suma del coste del camino hasta el momento y el coste estimado restante a la solución.<br/>
En la clase HeuristicaCuadrado, nuestra metodología es calcular la suma de cada fila, columna y diagonal, y en función del valor obtenido asignamos una puntuación y luego, utilizamos esa puntuación para ordenar esos posibles estados.<br/>
**Puntuacion 0** -> Fila/columna o diagonal completa y tiene el valor correcto.<br/>
**Puntuacion 1** -> Fila/columna o diagonal incompleta y valor menor al deseado.<br/>
**Puntuacion 1000** -> Fila/columna o diagonal con un valor superior al deseado.<br/>
La heurística del estado será igual a la suma de las puntuaciones de todas sus filas,columnas y diagonales.<br/>
Por lo tanto, nuestra heurística si es admisible, ya que el valor que proporciona nunca es sobreestimado, ya que solo da la puntuación 1000 cuando el estado es indeseable.<br/>
Nuestra función heurística no es consistente, ya que los valores de la función *f* a lo largo de los caminos recorridos son en ocasiones decrecientes.<br/>
## 2.c)
En este ejercicio implementamos una mejora en la función esAplicable de la clase ProblemaCuadradoMágico la cual nos permite asegurarnos que la suma de los elementos de ninguna fila,columna o diagonal se pase del número máximo, mediante condicionales los cuales retornan falso cuando uno de ellos se sobrepasa
. No obstante, no fue suficiente para la optimización de la búsqueda del cuadrado 4x4 con 6 o menos elementos (con 7 números dados es muy veloz).<br/>
