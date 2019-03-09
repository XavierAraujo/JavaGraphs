package com.xavier.graph;

class Vertex {

    private long id;
    private Object data;

    Vertex(long id) {
        this.id = id;
        this.data = null;
    }

    Vertex(long id, Object data) {
        this.id = id;
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public long getId() {
        return id;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (getClass() != obj.getClass()) {
            return false;
        }
        return this.id == ((Vertex) obj).id;
    }

    @Override
    public int hashCode()
    {
        return (int)(id ^ (id >>> 32));
    }
}
