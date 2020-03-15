// THIS FILE IS AUTO-GENERATED. DO NOT EDIT
package ai.verta.modeldb.versioning.autogenerated._public.modeldb.versioning.model;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import ai.verta.modeldb.ModelDBException;
import ai.verta.modeldb.versioning.*;
import ai.verta.modeldb.versioning.blob.visitors.Visitor;

public class DockerEnvironmentDiff {
    public DockerEnvironmentBlob A;
    public DockerEnvironmentBlob B;

    public DockerEnvironmentDiff() {
        this.A = null;
        this.B = null;
    }

    public DockerEnvironmentDiff setA(DockerEnvironmentBlob value) {
        this.A = value;
        return this;
    }
    public DockerEnvironmentDiff setB(DockerEnvironmentBlob value) {
        this.B = value;
        return this;
    }

    static public DockerEnvironmentDiff fromProto(ai.verta.modeldb.versioning.DockerEnvironmentDiff blob) {
        if (blob == null) {
            return null;
        }

        DockerEnvironmentDiff obj = new DockerEnvironmentDiff();
        {
            Function<ai.verta.modeldb.versioning.DockerEnvironmentDiff,DockerEnvironmentBlob> f = x -> DockerEnvironmentBlob.fromProto(blob.getA());
            obj.A = f.apply(blob);
        }
        {
            Function<ai.verta.modeldb.versioning.DockerEnvironmentDiff,DockerEnvironmentBlob> f = x -> DockerEnvironmentBlob.fromProto(blob.getB());
            obj.B = f.apply(blob);
        }
        return obj;
    }

    public ai.verta.modeldb.versioning.DockerEnvironmentDiff.Builder toProto() {
        ai.verta.modeldb.versioning.DockerEnvironmentDiff.Builder builder = ai.verta.modeldb.versioning.DockerEnvironmentDiff.newBuilder();
        {
            if (this.A != null) {
                Function<ai.verta.modeldb.versioning.DockerEnvironmentDiff.Builder,Void> f = x -> { builder.setA(this.A.toProto()); return null; };
                f.apply(builder);
            }
        }
        {
            if (this.B != null) {
                Function<ai.verta.modeldb.versioning.DockerEnvironmentDiff.Builder,Void> f = x -> { builder.setB(this.B.toProto()); return null; };
                f.apply(builder);
            }
        }
        return builder;
    }

    public void preVisitShallow(Visitor visitor) throws ModelDBException {
        visitor.preVisitDockerEnvironmentDiff(this);
    }

    public void preVisitDeep(Visitor visitor) throws ModelDBException {
        this.preVisitShallow(visitor);
        visitor.preVisitDeepDockerEnvironmentBlob(this.A);
        visitor.preVisitDeepDockerEnvironmentBlob(this.B);
    }

    public DockerEnvironmentDiff postVisitShallow(Visitor visitor) throws ModelDBException {
        return visitor.postVisitDockerEnvironmentDiff(this);
    }

    public DockerEnvironmentDiff postVisitDeep(Visitor visitor) throws ModelDBException {
        this.A = visitor.postVisitDeepDockerEnvironmentBlob(this.A);
        this.B = visitor.postVisitDeepDockerEnvironmentBlob(this.B);
        return this.postVisitShallow(visitor);
    }
}
