// THIS FILE IS AUTO-GENERATED. DO NOT EDIT
package ai.verta.modeldb.versioning.autogenerated._public.modeldb.versioning.model;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import ai.verta.modeldb.ModelDBException;
import ai.verta.modeldb.versioning.*;
import ai.verta.modeldb.versioning.blob.visitors.Visitor;

public class HyperparameterConfigDiff {
    public List<HyperparameterConfigBlob> A;
    public List<HyperparameterConfigBlob> B;

    public HyperparameterConfigDiff() {
        this.A = null;
        this.B = null;
    }

    public HyperparameterConfigDiff setA(List<HyperparameterConfigBlob> value) {
        this.A = value;
        return this;
    }
    public HyperparameterConfigDiff setB(List<HyperparameterConfigBlob> value) {
        this.B = value;
        return this;
    }

    static public HyperparameterConfigDiff fromProto(ai.verta.modeldb.versioning.HyperparameterConfigDiff blob) {
        if (blob == null) {
            return null;
        }

        HyperparameterConfigDiff obj = new HyperparameterConfigDiff();
        {
            Function<ai.verta.modeldb.versioning.HyperparameterConfigDiff,List<HyperparameterConfigBlob>> f = x -> blob.getAList().stream().map(HyperparameterConfigBlob::fromProto).collect(Collectors.toList());
            obj.A = f.apply(blob);
        }
        {
            Function<ai.verta.modeldb.versioning.HyperparameterConfigDiff,List<HyperparameterConfigBlob>> f = x -> blob.getBList().stream().map(HyperparameterConfigBlob::fromProto).collect(Collectors.toList());
            obj.B = f.apply(blob);
        }
        return obj;
    }

    public ai.verta.modeldb.versioning.HyperparameterConfigDiff.Builder toProto() {
        ai.verta.modeldb.versioning.HyperparameterConfigDiff.Builder builder = ai.verta.modeldb.versioning.HyperparameterConfigDiff.newBuilder();
        {
            if (this.A != null) {
                Function<ai.verta.modeldb.versioning.HyperparameterConfigDiff.Builder,Void> f = x -> { builder.addAllA(this.A.stream().map(y -> y.toProto().build()).collect(Collectors.toList())); return null; };
                f.apply(builder);
            }
        }
        {
            if (this.B != null) {
                Function<ai.verta.modeldb.versioning.HyperparameterConfigDiff.Builder,Void> f = x -> { builder.addAllB(this.B.stream().map(y -> y.toProto().build()).collect(Collectors.toList())); return null; };
                f.apply(builder);
            }
        }
        return builder;
    }

    public void preVisitShallow(Visitor visitor) throws ModelDBException {
        visitor.preVisitHyperparameterConfigDiff(this);
    }

    public void preVisitDeep(Visitor visitor) throws ModelDBException {
        this.preVisitShallow(visitor);
        visitor.preVisitDeepListOfHyperparameterConfigBlob(this.A);
        visitor.preVisitDeepListOfHyperparameterConfigBlob(this.B);
    }

    public HyperparameterConfigDiff postVisitShallow(Visitor visitor) throws ModelDBException {
        return visitor.postVisitHyperparameterConfigDiff(this);
    }

    public HyperparameterConfigDiff postVisitDeep(Visitor visitor) throws ModelDBException {
        this.A = visitor.postVisitDeepListOfHyperparameterConfigBlob(this.A);
        this.B = visitor.postVisitDeepListOfHyperparameterConfigBlob(this.B);
        return this.postVisitShallow(visitor);
    }
}
