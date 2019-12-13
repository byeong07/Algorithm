node = int(input())
tree_info = [[] for _ in range(node + 1)]

for _ in range(node - 1):
    parent, child, length = map(int, input().split())
    tree_info[parent].append((child, length))


#BFS
def bfs(graph_list, start):
    visited = []
    queue = [start]
    
    while queue:
        node = queue.pop(0)
        visited.append(node)
        for child in graph_list[node]:
            queue.append(child[0])
            
    return visited
    
    
#list creation for sorting
bfs_node = bfs(tree_info, 1)
