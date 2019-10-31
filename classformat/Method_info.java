import java.util.ArrayList;

class Method_info {
    public int access_flags;
    public int name_index;
    public int descriptor_index;
    public int attributes_count;
    public ArrayList<Attribute_info> attributes_list;

    public Method_info(){
        this.access_flags = access_flags;
        this.name_index = name_index;
        this.descriptor_index = descriptor_index;
        this.attributes_count = attributes_count;
        this.attributes_list = new ArrayList<Attribute_info>();
    }
}
